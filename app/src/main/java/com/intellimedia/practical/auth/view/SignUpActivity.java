package com.intellimedia.practical.auth.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.intellimedia.practical.R;
import com.intellimedia.practical.auth.model.UserTable;
import com.intellimedia.practical.auth.presenter.SignUpPresenter;
import com.intellimedia.practical.db.UserDao;
import com.intellimedia.practical.utils.Utilities;
import com.intellimedia.practical.utils.Validator;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText editUsername, editPassword, editEmail, editConfirmPassword, editFullName, editCity, editState, editAddress;
    AppCompatButton btnSubmit;
    Spinner spnrCountryPicker;

    String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
            "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas",
            "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
            "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam",
            "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands",
            "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia",
            "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire",
            "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
            "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia",
            "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana",
            "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar",
            "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
            "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India",
            "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan",
            "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait",
            "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya",
            "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar",
            "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius",
            "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat",
            "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles",
            "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands",
            "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn",
            "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda",
            "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
            "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore",
            "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
            "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon",
            "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic",
            "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga",
            "Trinidad and Tobago", "Tunisia", "Türkiye", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine",
            "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay",
            "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)",
            "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};

    SignUpPresenter signUpPresenter;

    String selectCountry = countries[0];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        initUI();
        initListeners();
        signUpPresenter = new SignUpPresenter();

        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(this, countries);
        spnrCountryPicker.setAdapter(customSpinnerAdapter);

    }

    private void initUI() {
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editEmail = findViewById(R.id.editEmail);
        editConfirmPassword = findViewById(R.id.editConfirmPassword);
        editCity = findViewById(R.id.editCity);
        editFullName = findViewById(R.id.editFullName);
        editState = findViewById(R.id.editState);
        editAddress = findViewById(R.id.editAddress);
        spnrCountryPicker = findViewById(R.id.spnrCountryPicker);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void initListeners() {
        btnSubmit.setOnClickListener(this);
        spnrCountryPicker.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        if (Validator.isFieldBlank(editUsername.getText().toString())) {
            editUsername.setError(Utilities.getBlankErrorMessage(this, "Username"));
        } else if (Validator.containsSpace(editUsername.getText().toString())) {
            editUsername.setError(Utilities.getSpaceErrorMessage(this, "Username"));
        } else if (Validator.isFieldBlank(editFullName.getText().toString())) {
            editFullName.setError(Utilities.getBlankErrorMessage(this, "Full Name"));
        } else if (!Validator.isFullNameValid(editFullName.getText().toString())) {
            editFullName.setError(Utilities.getInvalidErrorMessage(this, "Full Name"));
        } else if (Validator.isFieldBlank(editEmail.getText().toString())) {
            editEmail.setError(Utilities.getBlankErrorMessage(this, "Email"));
        } else if (!Validator.isEmailValid(editEmail.getText().toString())) {
            editEmail.setError(Utilities.getInvalidErrorMessage(this, "Email"));
        } else if (!Validator.isPasswordMatching(editPassword.getText().toString(), editConfirmPassword.getText().toString())) {
            editConfirmPassword.setError(getString(R.string.errorPasswordNotMatch));
        } else if (Validator.isFieldBlank(editAddress.getText().toString())) {
            editAddress.setError(Utilities.getInvalidErrorMessage(this, "Address"));
        } else if (Validator.isFieldBlank(editCity.getText().toString())) {
            editCity.setError(Utilities.getSpaceErrorMessage(this, "City"));
        } else if (Validator.containsSpace(editCity.getText().toString())) {
            editCity.setError(Utilities.getInvalidErrorMessage(this, "City"));
        } else if (Validator.isFieldBlank(editState.getText().toString())) {
            editState.setError(Utilities.getSpaceErrorMessage(this, "State"));
        } else if (Validator.containsSpace(editState.getText().toString())) {
            editState.setError(Utilities.getInvalidErrorMessage(this, "State"));
        } else if (Validator.isFieldBlank(editPassword.getText().toString())) {
            editPassword.setError(Utilities.getInvalidErrorMessage(this, "Password"));
        } else if (Validator.containsSpace(editPassword.getText().toString())) {
            editPassword.setError(Utilities.getSpaceErrorMessage(this, "Password"));
        } else if (Validator.isFieldBlank(editConfirmPassword.getText().toString())) {
            editConfirmPassword.setError(Utilities.getInvalidErrorMessage(this, "Password"));
        } else if (Validator.containsSpace(editConfirmPassword.getText().toString())) {
            editConfirmPassword.setError(Utilities.getSpaceErrorMessage(this, "Password"));
        } else if (signUpPresenter.isUserNameAvailable(editUsername.getText().toString())) {
            editUsername.requestFocus();
            editUsername.setError(Utilities.getAlreadyPresenetErrorMessage(this, "Username"));
        } else if (signUpPresenter.isUserEmailAvailable(editEmail.getText().toString())) {
            editEmail.requestFocus();
            editEmail.setError(Utilities.getAlreadyPresenetErrorMessage(this, "Email"));
        } else {
            UserTable userTable = new UserTable();
            userTable.setUsername(editUsername.getText().toString());
            userTable.setFullname(editFullName.getText().toString());
            userTable.setEmail(editEmail.getText().toString());
            userTable.setCity(editCity.getText().toString());
            userTable.setState(editState.getText().toString());
            userTable.setCountry(selectCountry);
            userTable.setPassword(Utilities.md5(editPassword.getText().toString()));
            userTable.setAddress(editAddress.getText().toString());
            signUpPresenter.addUser(userTable);
            startActivity(new Intent(SignUpActivity.this, ProfileActivity.class));
            SignUpActivity.this.finish();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectCountry = countries[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
