package com.gabriel.aranias.mareu.controller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriel.aranias.mareu.R;
import com.gabriel.aranias.mareu.model.Meeting;

import java.util.List;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> meetings;

    public MeetingRecyclerViewAdapter(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
       Meeting meeting = meetings.get(position);
       holder.meetingInfo.setText(String.format("%s - %s - %s", meeting.getTopic(),
               meeting.getTime(), meeting.getRoom()));
        // TODO: 10/08/2021 display list of attendees + room icon + delete btn
        // TODO: 10/08/2021 setOnClickListener meeting details + delete btn
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       public ImageView roomIcon;
       public TextView meetingInfo;
       public TextView meetingAttendees;
       public ImageButton deleteBtn;

       public ViewHolder(View itemView) {
           super(itemView);
           roomIcon = itemView.findViewById(R.id.room_icon);
           meetingInfo = itemView.findViewById(R.id.meeting_info);
           meetingAttendees = itemView.findViewById(R.id.meeting_attendees);
           deleteBtn = itemView.findViewById(R.id.delete_btn);
       }
    }
}