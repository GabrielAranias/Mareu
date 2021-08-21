package com.gabriel.aranias.mareu.controller.fragments;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.gabriel.aranias.mareu.R;
import com.gabriel.aranias.mareu.controller.adapter.RoomSpinnerAdapter;
import com.gabriel.aranias.mareu.databinding.FragmentAddMeetingBinding;
import com.gabriel.aranias.mareu.di.DI;
import com.gabriel.aranias.mareu.model.Meeting;
import com.gabriel.aranias.mareu.model.Room;
import com.gabriel.aranias.mareu.service.MeetingApiService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AddMeetingFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public AddMeetingFragment() {
    }

    private FragmentAddMeetingBinding binding;
    private ArrayList<Room> rooms;
    private int tHour, tMinute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddMeetingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initRoomList();
        setRoomSpinner();
        setTimePicker();
        checkMeeting();

        return view;
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

    // Set the spinner allowing users to choose a meeting room
    private void setRoomSpinner() {
        Spinner spinner = binding.roomSpinner;
        RoomSpinnerAdapter adapter = new RoomSpinnerAdapter(this.getActivity(), rooms);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Room clickedRoom = (Room) parent.getItemAtPosition(position);
                String clickedRoomName = clickedRoom.getRoomName();
                Toast.makeText(getActivity(),"Salle '" + clickedRoomName + "' sélectionnée",
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

    // Set the time picker allowing users to choose a meeting time
    private void setTimePicker() {
        TextView timePicker = binding.timePicker;

        Objects.requireNonNull(timePicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tpd = new TimePickerDialog(getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tHour = hourOfDay;
                        tMinute = minute;
                        String time = String.format(Locale.FRANCE,"%02d:%02d", tHour, tMinute);
                        SimpleDateFormat f24Hours = new SimpleDateFormat(time, Locale.FRANCE);
                        try {
                            Date date = f24Hours.parse(time);
                            assert date != null;
                            timePicker.setText(f24Hours.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, 12, 0, true
                );
                tpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                tpd.updateTime(tHour, tMinute);
                tpd.show();
            }
        });
    }

    // Add meeting w/ details to existing list when user clicks on fab
    private void checkMeeting() {
        MeetingApiService service = DI.getMeetingApiService();
        binding.checkAddMeetingFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Meeting meeting = new Meeting((Room) binding.roomSpinner.getSelectedItem(),
                        Objects.requireNonNull(binding.topicEt.getText()).toString(),
                        binding.timePicker.getText().toString(),
                        Objects.requireNonNull(binding.attendeesEt.getText()).toString());

                service.createMeeting(meeting);
                Toast.makeText(getActivity(),"Réunion enregistrée", Toast.LENGTH_SHORT).show();
                requireActivity().finish();
            }
        });
    }
}