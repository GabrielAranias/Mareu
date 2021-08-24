package com.gabriel.aranias.mareu.controller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriel.aranias.mareu.databinding.MeetingItemBinding;
import com.gabriel.aranias.mareu.model.Attendee;
import com.gabriel.aranias.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private List<Meeting> meetings = new ArrayList<>();
    private onMeetingClickListener clickListener;
    private Meeting currentMeeting;

    public void updateMeetingList(List<Meeting> meetings, onMeetingClickListener listener) {
        this.meetings = meetings;
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MeetingItemBinding binding = MeetingItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(meetings.get(position));
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final MeetingItemBinding binding;

        public ViewHolder(@NonNull MeetingItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            this.binding.meetingInfoItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onMeetingClicked(getAdapterPosition());
                }
            });

            this.binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onDeleteBtnClicked(getAdapterPosition());
                }
            });
        }

        // Display meeting list
        public void bindView(Meeting meeting) {
            currentMeeting = meeting;
            binding.roomIconItem.setImageResource(meeting.getRoom().getRoomIcon());
            binding.meetingInfoItem.setText(String.format("%s - %s - %s", meeting.getTopic(),
                    meeting.getTime(), meeting.getRoom().getRoomName()));
            binding.meetingAttendeesItem.setText(getMeetingAttendees());
        }

        //Display properly list of attendees
        private StringBuilder getMeetingAttendees() {
            StringBuilder sb = new StringBuilder();
            for (Attendee attendee : currentMeeting.getAttendees()) {
                sb.append(attendee.getAttendee());
                if (currentMeeting.getAttendees().size()-1 != currentMeeting.getAttendees().indexOf(attendee)) {
                    sb.append(", ");
                }
            }
            return sb;
        }
    }
}