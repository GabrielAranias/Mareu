package com.gabriel.aranias.mareu.controller.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriel.aranias.mareu.databinding.MeetingItemBinding;
import com.gabriel.aranias.mareu.model.Attendee;
import com.gabriel.aranias.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder>
        implements Filterable {

    private List<Meeting> meetings, meetingsAll, filteredList;
    private onMeetingClickListener listener;
    private Meeting currentMeeting;

    public void updateMeetingList(List<Meeting> meetings, onMeetingClickListener listener) {
        this.meetings = meetings;
        this.listener = listener;
        meetingsAll = new ArrayList<>(meetings);
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

    @Override
    public Filter getFilter() {
        return roomFilter;
    }

    public Filter getTimeFilter() {
        return timeFilter;
    }

    // Set room filter allowing user to search for meetings in a specific room
    private final Filter roomFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(meetingsAll);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Meeting filteredMeeting : meetingsAll) {
                    if (filteredMeeting.getRoom().getRoomName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(filteredMeeting);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            meetings.clear();
            meetings.addAll((Collection<? extends Meeting>) results.values);
            notifyDataSetChanged();
        }
    };

    // Set time filter allowing user to search for meetings at a specific time
    private final Filter timeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(meetingsAll);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Meeting filteredMeeting : meetingsAll) {
                    if (filteredMeeting.getTime().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(filteredMeeting);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            meetings.clear();
            meetings.addAll((Collection<? extends Meeting>) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final MeetingItemBinding binding;

        public ViewHolder(@NonNull MeetingItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            Objects.requireNonNull(this.binding.meetingItem).setOnClickListener(v ->

                    listener.onMeetingClicked(meetings.get(getAdapterPosition())));

            this.binding.deleteBtn.setOnClickListener(v ->

                    listener.onDeleteBtnClicked(meetings.get(getAdapterPosition())));
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
                if (currentMeeting.getAttendees().size() - 1 != currentMeeting.getAttendees()
                        .indexOf(attendee)) {
                    sb.append(", ");
                }
            }
            return sb;
        }
    }
}