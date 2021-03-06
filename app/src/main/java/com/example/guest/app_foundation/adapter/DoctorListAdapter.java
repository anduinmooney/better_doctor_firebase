package com.example.guest.app_foundation.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.app_foundation.R;
import com.example.guest.app_foundation.models.Doctor;
import com.example.guest.app_foundation.ui.DoctorDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    private ArrayList<Doctor> mDoctors = new ArrayList<>();
    private Context mContext;

    public DoctorListAdapter(Context context, ArrayList<Doctor> doctors) {
        mContext = context;
        mDoctors = doctors;
    }

    @Override
    public DoctorListAdapter.DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_layout, parent, false);
        DoctorViewHolder viewHolder = new DoctorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DoctorListAdapter.DoctorViewHolder holder, int position) {
        holder.bindDoctor(mDoctors.get(position));
    }

    @Override
    public int getItemCount() {
        return mDoctors.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.doctorImageView) ImageView mDoctorImageView;
        @BindView(R.id.doctorFirstNameTextView) TextView mFirstNameTextView;
        @BindView(R.id.doctorLastNameTextView) TextView mLastNameTextView;
        @BindView(R.id.addressTextView) TextView mAddressTextView;
        @BindView(R.id.phoneTextView) TextView mPhoneTextView;
        private Context mContext;

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, DoctorDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("doctors", Parcels.wrap(mDoctors));
            mContext.startActivity(intent);
        }



        public DoctorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindDoctor(Doctor doctor) {
            mFirstNameTextView.setText(doctor.getFirstName());
            mLastNameTextView.setText(doctor.getLastName());
            mAddressTextView.setText(doctor.getAddress());
            mPhoneTextView.setText(doctor.getPhone());
            Picasso.with(mContext)
                    .load(doctor.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mDoctorImageView);
        }
    }
}
