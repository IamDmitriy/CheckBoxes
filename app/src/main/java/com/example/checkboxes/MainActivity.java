package com.example.checkboxes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText mInputMoney;
    private EditText mInputInfo;
    private Button mBtnOk;
    private CheckBox mBankCardChkBx;
    private CheckBox mMobilePhoneChkBx;
    private CheckBox mCashAddressChkBx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mInputMoney = findViewById(R.id.inputMoney);
        mInputInfo = findViewById(R.id.inputInfo);
        mBtnOk = findViewById(R.id.btnOK);
        mBankCardChkBx = findViewById(R.id.bankCardChkBx);
        mMobilePhoneChkBx = findViewById(R.id.mobilePhoneChkBx);
        mCashAddressChkBx = findViewById(R.id.cashAddressChkBx);

        mInputInfo.setEnabled(false);

        mBankCardChkBx.setOnCheckedChangeListener(checkedChangeListener);
        mMobilePhoneChkBx.setOnCheckedChangeListener(checkedChangeListener);
        mCashAddressChkBx.setOnCheckedChangeListener(checkedChangeListener);

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtInputMoney = mInputMoney.getText().toString();
                String txtInputInfo = mInputInfo.getText().toString();

                if (txtInputInfo.equals("") || txtInputMoney.equals("")) {
                    showToast("Пожалуйста, заполните все поля!");
                } else {
                    showToast("Сумма " + txtInputMoney + "р. с " + txtInputInfo);
                }
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void resetCheckBoxes() {
        mBankCardChkBx.setChecked(false);
        mMobilePhoneChkBx.setChecked(false);
        mCashAddressChkBx.setChecked(false);
    }


    CompoundButton.OnCheckedChangeListener checkedChangeListener =
            new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        mInputInfo.setText("");
                        resetCheckBoxes();
                        mInputInfo.setEnabled(true);

                        switch (compoundButton.getId()) {
                            case R.id.bankCardChkBx:
                                mInputInfo.setInputType(InputType.TYPE_CLASS_NUMBER);
                                mBankCardChkBx.setChecked(true);
                                break;
                            case R.id.mobilePhoneChkBx:
                                mMobilePhoneChkBx.setChecked(true);
                                mInputInfo.setInputType(InputType.TYPE_CLASS_PHONE);
                                break;
                            case R.id.cashAddressChkBx:
                                mCashAddressChkBx.setChecked(true);
                                mInputInfo.setInputType(InputType.TYPE_CLASS_TEXT);
                                break;
                            default:

                        }
                    } else {
                        mInputInfo.setEnabled(false);
                        mInputInfo.setText("");
                    }
                }
            };
}
