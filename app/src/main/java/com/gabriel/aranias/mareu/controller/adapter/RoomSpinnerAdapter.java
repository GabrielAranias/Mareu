package com.gabriel.aranias.mareu.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gabriel.aranias.mareu.R;
import com.gabriel.aranias.mareu.model.Room;

import java.util.ArrayList;

public class RoomSpinnerAdapter extends ArrayAdapter<Room> {

    public RoomSpinnerAdapter(Context context, ArrayList<Room> rooms) {
        super(context, 0, rooms);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.room_spinner_row,
                    parent, false);
        }

        ImageView roomIcon = convertView.findViewById(R.id.room_iv);
        TextView roomName = convertView.findViewById(R.id.room_tv);

        Room currentRoom = getItem(position);

        if (currentRoom != null) {
            roomIcon.setImageResource(currentRoom.getRoomIcon());
            roomName.setText(currentRoom.getRoomName());
        }

        return convertView;
    }
}
