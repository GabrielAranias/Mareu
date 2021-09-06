package com.gabriel.aranias.mareu.controller.fragments;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gabriel.aranias.mareu.R;
import com.gabriel.aranias.mareu.controller.adapter.RoomSpinnerAdapter;
import com.gabriel.aranias.mareu.databinding.FragmentAddMeetingBinding;
import com.gabriel.aranias.mareu.di.DI;
import com.gabriel.aranias.mareu.model.Attendee;
import com.gabriel.aranias.mareu.model.Meeting;
import com.gabriel.aranias.mareu.model.Room;
import com.gabriel.aranias.mareu.service.MeetingApiService;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AddMeetingFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public AddMeetingFragment() {
    }

    private FragmentAddMeetingBinding binding;
    private ArrayList<Room> rooms;
    private int tHour, tMinute;
    private final List<Attendee> attendees = new ArrayList<>();
    private boolean clicked = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddMeetingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRoomList();
        setRoomSpinner();
        setTimePicker();
        addAttendees();
        checkMeeting();
    }

    // Init the list of meeting rooms with their respective names and icons
    private void initRoomList() {
        rooms = new ArrayList<>();
        rooms.add(new Room("Bowser", R.drawable.bowser));
        rooms.add(new Room("Harmonie", R.drawable.harmonie));
        rooms.add(new Room("Larry", R.drawable.larry));
        rooms.add(new Room("Ludwig", R.drawable.ludwig));
        rooms.add(new Room("Luigi", R.drawable.luigi));
        rooms.add(new Room("Mario", R.drawable.mario));
        rooms.add(new Room("Peach", R.drawable.peach));
        rooms.add(new Room("Roi Boo", R.drawable.roi_boo));
        rooms.add(new Room("Waluigi", R.drawable.waluigi));
        rooms.add(new Room("Yoshi", R.drawable.yoshi));
    }

    // Set the spinner allowing user to choose a meeting room
    private void setRoomSpinner() {
        Spinner spinner = binding.roomSpinner;
        RoomSpinnerAdapter adapter = new RoomSpinnerAdapter(this.getActivity(), rooms);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Room clickedRoom = (Room) parent.getItemAtPosition(position);
                String clickedRoomName = clickedRoom.getRoomName();
                Toast.makeText(getActivity(), "Salle '" + clickedRoomName + "' sélectionnée",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    // Set the time picker allowing user to choose a meeting time
    private void setTimePicker() {
        TextView timePicker = binding.timePicker;

        Objects.requireNonNull(timePicker).setOnClickListener(view -> {
            TimePickerDialog tpd = new TimePickerDialog(getActivity(),
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    (view1, hourOfDay, minute) -> {
                        tHour = hourOfDay;
                        tMinute = minute;
                        String time = String.format(Locale.FRANCE, "%02d:%02d", tHour, tMinute);
                        SimpleDateFormat f24Hours = new SimpleDateFormat(time, Locale.FRANCE);
                        try {
                            Date date = f24Hours.parse(time);
                            assert date != null;
                            timePicker.setText(f24Hours.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }, 12, 0, true
            );
            tpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            tpd.updateTime(tHour, tMinute);
            tpd.show();
        });
    }

    // Set the ChipGroup allowing user to add/remove attendees to/from a meeting
    private void addAttendees() {
        binding.addAttendeeBtn.setOnClickListener(v -> {
            clicked = true;

            ChipGroup chipGroup = binding.attendeesChipGroup;
            String[] emails = Objects.requireNonNull(binding.attendeesEt.getText()).toString()
                    .split(" ");
            LayoutInflater inflater = LayoutInflater.from(getContext());

            for (String email : emails) {
                @SuppressLint("InflateParams") Chip chip = (Chip) inflater
                        .inflate(R.layout.attendee_chip_item, null, false);
                chip.setText(email);

                Attendee addedAttendee = new Attendee(chip.getText().toString());
                attendees.add(addedAttendee);
                chipGroup.addView(chip);
                binding.attendeesEt.setText("");

                chip.setOnCloseIconClickListener(v1 -> {
                            chipGroup.removeView(v1);
                            attendees.remove(addedAttendee);
                        }
                );
            }
        });
    }

    // When user clicks on fab to add a new meeting...
    private void checkMeeting() {
        FloatingActionButton fab = binding.checkAddMeetingFab;
        fab.setOnClickListener(v -> setErrorOnSubmit());
    }

    // ...Set error if one of the fields to be filled is empty, else create meeting
    private void setErrorOnSubmit() {
        if (Objects.requireNonNull(binding.topicTil.getEditText()).getText().toString().isEmpty()) {
            binding.topicTil.setError(getString(R.string.topic_error));
            return;
        }
        if (!clicked) {
            binding.attendeesTil.setError(getString(R.string.attendees_error));
            return;
        }
        if (binding.timePicker.getText().toString().isEmpty()) {
            binding.timePicker.setError(getString(R.string.time_error));
            binding.timeLayout.setBackground(ContextCompat.getDrawable(requireContext(),
                    R.drawable.back_fields_error_drawable));
            Toast.makeText(getActivity(), R.string.time_error, Toast.LENGTH_SHORT).show();
            return;
        }
        createMeeting();
    }

    // If all fields are filled, add meeting w/ details to existing list
    private void createMeeting() {
        MeetingApiService service = DI.getMeetingApiService();
        Meeting meeting = new Meeting((Room) binding.roomSpinner.getSelectedItem(),
                Objects.requireNonNull(binding.topicEt.getText()).toString(),
                binding.timePicker.getText().toString(), attendees);
        service.createMeeting(meeting);
        Toast.makeText(getActivity(), R.string.saved, Toast.LENGTH_SHORT).show();
        requireActivity().finish();
    }
}
