package com.ugb.calculatorviewbindingasp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ugb.calculatorviewbindingasp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view =binding.getRoot();
        setContentView(view);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double num1 = Double.parseDouble(binding.txtNum1.getText().toString());
                    double num2 = Double.parseDouble(binding.txtNum2.getText().toString());
                    double respuesta = 0;

                    int checkedRadioButtonId = binding.radioOpciones.getCheckedRadioButtonId();
                    if (checkedRadioButtonId == R.id.suma) {
                        respuesta = num1 + num2;
                    } else if (checkedRadioButtonId == R.id.resta) {
                        respuesta = num1 - num2;
                    } else if (checkedRadioButtonId == R.id.multi) {
                        respuesta = num1 * num2;
                    } else if (checkedRadioButtonId == R.id.div) {
                        respuesta = num1 / num2;
                    } else if (checkedRadioButtonId == R.id.exp) {
                        respuesta = Math.pow(num1, num2);
                    }

                    binding.lblResp.setText("Respuesta: " + respuesta);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Ingresa números válidos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
