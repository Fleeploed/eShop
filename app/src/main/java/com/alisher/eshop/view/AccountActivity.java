package com.alisher.eshop.view;

import static com.alisher.eshop.storage.LanguageUtils.getEnglishState;
import static com.alisher.eshop.storage.LanguageUtils.loadLocale;
import static com.alisher.eshop.storage.LanguageUtils.setEnglishState;
import static com.alisher.eshop.storage.LanguageUtils.setLocale;
import static com.alisher.eshop.utils.CommunicateUtils.rateAppOnGooglePlay;
import static com.alisher.eshop.utils.CommunicateUtils.shareApp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.alisher.eshop.R;
import com.alisher.eshop.databinding.ActivityAccountBinding;
import com.alisher.eshop.storage.LoginUtils;
import com.alisher.eshop.utils.FlagsManager;
import com.alisher.eshop.viewmodel.FromHistoryViewModel;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AccountActivity";
    private FromHistoryViewModel fromHistoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        ActivityAccountBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_account);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.my_account));

        fromHistoryViewModel = ViewModelProviders.of(this).get(FromHistoryViewModel.class);

        binding.nameOfUser.setText(LoginUtils.getInstance(this).getUserInfo().getName());
        binding.emailOfUser.setText(LoginUtils.getInstance(this).getUserInfo().getEmail());

        binding.languages.setOnClickListener(this);
        binding.helpCenter.setOnClickListener(this);
        binding.shareWithFriends.setOnClickListener(this);
        binding.rateUs.setOnClickListener(this);
        binding.changePassword.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_signOut) {
            signOut();
            deleteAllProductsInHistory();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void signOut() {
        LoginUtils.getInstance(this).clearAll();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void deleteAllProductsInHistory() {
       fromHistoryViewModel.removeAllFromHistory().observe(this, responseBody -> Log.d(TAG,getString(R.string.all_removed)));
       FlagsManager.getInstance().setHistoryDeleted(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.languages:
                showCustomAlertDialog();
                break;
            case R.id.helpCenter:
                Intent helpCenterIntent = new Intent(this, HelpActivity.class);
                startActivity(helpCenterIntent);
                break;
            case R.id.shareWithFriends:
                shareApp(this);
                break;
            case R.id.rateUs:
                rateAppOnGooglePlay(this);
                break;
            case R.id.changePassword:
                Intent passwordIntent = new Intent(this, PasswordActivity.class);
                startActivity(passwordIntent);
                break;

            default:
        }
    }



    private void showCustomAlertDialog() {
        final Dialog dialog = new Dialog(AccountActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_language_dialog);

        Button english = dialog.findViewById(R.id.txtEnglish);
        Button czech = dialog.findViewById(R.id.txtCzech);

        if(getEnglishState(this)){
            english.setEnabled(false);
            english.setAlpha(.5f);
            czech.setEnabled(true);
        }else {
            czech.setEnabled(false);
            czech.setAlpha(.5f);
            english.setEnabled(true);
        }

        english.setOnClickListener(v -> {
            english.setEnabled(true);
            chooseEnglish();
            dialog.cancel();
        });

        czech.setOnClickListener(v -> {
            czech.setEnabled(true);
            chooseCzech();
            dialog.cancel();
        });

        dialog.show();
    }

    private void chooseEnglish() {
        setLocale(this,"en");
        recreate();
        Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
        setEnglishState(this, true);
    }

    private void chooseCzech() {
        setLocale(this,"cs");
        recreate();
        Toast.makeText(this, "Čeština", Toast.LENGTH_SHORT).show();
        setEnglishState(this, false);
    }
}