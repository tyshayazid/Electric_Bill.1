package com.example.electricbill;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private EditText etUnitInput;
    private Button btnCalculate;
    private TextView tvTotal;
    private Spinner monthSpinner;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        etUnitInput = view.findViewById(R.id.unit);
        btnCalculate = view.findViewById(R.id.btnCalculate);
        tvTotal = view.findViewById(R.id.total);

        // Set onClickListener for the Calculate button
        btnCalculate.setOnClickListener(v -> calculateBill());
    }

    private void calculateBill() {
        String unitInput = etUnitInput.getText().toString();

        // Check if the input is empty
        if (unitInput.isEmpty()) {
            tvTotal.setText("Please enter the number of units used.");
            return;
        }

        int units;
        try {
            // Parse the input to an integer
            units = Integer.parseInt(unitInput);
        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric value)
            tvTotal.setText("Invalid input. Please enter a valid number.");
            return;
        }

        // Calculate the total charges based on the slabs
        double totalCharges = 0.0;

        if (units > 0) {
            if (units <= 200) {
                totalCharges = units * 0.218;
            } else if (units <= 300) {
                totalCharges = (200 * 0.218) + ((units - 200) * 0.334);
            } else if (units <= 600) {
                totalCharges = (200 * 0.218) + (100 * 0.334) + ((units - 300) * 0.516);
            } else {
                totalCharges = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((units - 600) * 0.546);
            }
        }

        // Apply the 5% discount
        double finalCost = totalCharges - (totalCharges * 0.05);

        // Display the final cost
        tvTotal.setText(String.format("Total Bill: RM%.2f", finalCost));

        // Display a toast notification that the bill was calculated
        Toast.makeText(getContext(), "Bill calculated", Toast.LENGTH_SHORT).show();
    }
}
