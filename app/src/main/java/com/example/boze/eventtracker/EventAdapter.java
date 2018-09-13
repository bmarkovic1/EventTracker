package com.example.boze.eventtracker;

import android.location.Geocoder;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;


public class EventAdapter extends BaseAdapter {
    private ArrayList<Event> Events;
    public EventAdapter(ArrayList<Event> events) { Events = events; }
    @Override
    public int getCount() { return this.Events.size(); }
    @Override
    public Object getItem(int position) { return this.Events.get(position); }
    @Override
    public long getItemId(int position) { return position; }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder eventViewHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.event_item, parent, false);
            eventViewHolder = new ViewHolder(convertView);
            convertView.setTag(eventViewHolder);
        }
        else{
            eventViewHolder = (ViewHolder) convertView.getTag();
        }
        Event event = this.Events.get(position);
        eventViewHolder.tvEventType.setText(event.getType());
        eventViewHolder.tvEventTime.setText(String.valueOf(event.getTime()));
        eventViewHolder.tvEventMarker.setText(String.valueOf(event.getMarker().getLatitude())+ String.valueOf(event.getMarker().getLongitude()));
        return convertView;
    }


    public static class ViewHolder {
        public TextView tvEventType, tvEventTime, tvEventMarker;
        public ViewHolder(View bookView) {
            tvEventType = (TextView) bookView.findViewById(R.id.tvEventType);
            tvEventTime = (TextView) bookView.findViewById(R.id.tvEventTime);
            tvEventMarker = (TextView) bookView.findViewById(R.id.tvEventMarker);
        }
    }




}