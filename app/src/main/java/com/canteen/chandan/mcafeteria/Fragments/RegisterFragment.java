package com.canteen.chandan.mcafeteria.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.canteen.chandan.mcafeteria.MainActivity;
import com.canteen.chandan.mcafeteria.PreConfig;
import com.canteen.chandan.mcafeteria.R;
import com.canteen.chandan.mcafeteria.model.rest.ApiCall;
import com.canteen.chandan.mcafeteria.model.rest.ApiClient;
import com.canteen.chandan.mcafeteria.Beans.DataMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    private View infoview;
    private TextInputLayout name;
    private TextInputLayout card_id;
    private TextInputLayout email;
    private TextInputLayout contact;
    private TextInputLayout pass;
    private Button reg;
    private ProgressDialog mProgress;
    private static ApiCall apiCall;
    private PreConfig preConfig;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        infoview = inflater.inflate(R.layout.fragment_register, container, false);
        name = infoview.findViewById(R.id.r_name);
        card_id = infoview.findViewById(R.id.r_card_id);
        email = infoview.findViewById(R.id.r_email);
        contact = infoview.findViewById(R.id.r_contact);
        pass = infoview.findViewById(R.id.r_password);
        reg = infoview.findViewById(R.id.r_regbtn);
        mProgress = new ProgressDialog(this.getContext());
        mProgress.setTitle("Registering User");
        mProgress.setMessage("Wait for a while.we are signing you in.\nIt won't take long.");
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchDataRegister();
            }
        });

        return infoview;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preConfig=new PreConfig(getContext());
    }


    private void fetchDataRegister() {

        apiCall=ApiClient.getRetrofit().create(ApiCall.class);

        final String card_id = this.card_id.getEditText().getText().toString();

        String pass = this.pass.getEditText().getText().toString();
        String email = this.email.getEditText().getText().toString();
        String contact = this.contact.getEditText().getText().toString();
        String name = this.name.getEditText().getText().toString();

        if (validate(card_id, pass, email, contact, name)) {
            mProgress.show();
            Call<DataMap> call=apiCall.Register(name,card_id,contact,email,pass);
            call.enqueue(new Callback<DataMap>() {
                @Override
                public void onResponse(Call<DataMap> call, Response<DataMap> response) {
                   String res=response.body().getResponse();

                   if(res.equals("nu")){
                       preConfig.writeLoginStatus(true);
                       preConfig.writeCardId(card_id);
                       Toast.makeText(getActivity(), "SignUp Successful", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(getActivity(), MainActivity.class));
                       getActivity().finish();
                   }else if(res.equals("ex")){
                        Toast.makeText(getContext(), "UserAlready Exists", Toast.LENGTH_SHORT).show();
                   }
                }

                @Override
                public void onFailure(Call<DataMap> call, Throwable t) {
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            });
            mProgress.dismiss();
        }
    }

    private boolean validate(String card_id, String pass, String email, String contact, String name) {

        String[] message = new String[6];
        boolean status=false;

        message[5]="Fields like";

        if (card_id.isEmpty()) {
            message[1] = ",card_id";
        }
        if (email.isEmpty()) {
            message[2] = ",email";
        }
        if (pass.isEmpty()) {
            message[4] = ",password";
        }
        if (contact.isEmpty()) {
            message[3] = ",contact";
        }
        if (name.isEmpty()) {
            message[0] = " name";
        }
        for(int i=0;i<5;i++){
            if(message[i]!=null)
            message[5]=message[5].concat(message[i]);
        }
        if(message[5].equals("Fields like")) {
            status = true;
        }
        if(!status) {
            Toast.makeText(getContext(), message[5] + " cannot be empty", Toast.LENGTH_LONG).show();
        }
        return status;
    }
}
