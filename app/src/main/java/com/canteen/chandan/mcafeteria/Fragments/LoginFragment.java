package com.canteen.chandan.mcafeteria.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
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

public class LoginFragment extends Fragment {

    private TextInputLayout idctl;
    private TextInputLayout ptl;
    private Button lb;
    private View infoview;
    private Button regbtn;
    private ProgressDialog mProg;
    private static ApiCall apiCall;
    LoginInterface lInter;

    public interface LoginInterface{
        void regClicked();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mProg=new ProgressDialog(getContext());
        infoview = inflater.inflate(R.layout.fragment_login, container, false);
        regbtn=infoview.findViewById(R.id.registerbtn);
        idctl=infoview.findViewById(R.id.idcardtl);
        ptl=infoview.findViewById(R.id.passtl);
        lb=infoview.findViewById(R.id.loginbtn);

        idctl.getEditText().setText(new PreConfig(getContext()).readCardId());
        FetchDataLogin();
        apiCall= ApiClient.getRetrofit().create(ApiCall.class);
        return infoview;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        lInter=(LoginInterface) context;
    }

    private void FetchDataLogin(){

        lb.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               mProg.setTitle("Logging User");
               mProg.setMessage("Please wait\nIt won't take long....");
               mProg.show();
               String S1,S2;
               S1=idctl.getEditText().getText().toString();
               S2=ptl.getEditText().getText().toString();
               if(!S1.isEmpty() && !S2.isEmpty())
               loginUser(S1,S2);
               else{
                   Toast.makeText(getActivity(), "Fields can't be empty", Toast.LENGTH_SHORT).show();
               }
           }
       });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lInter.regClicked();
            }
        });
    }

    //Retrofit LoginFragment

    private void loginUser(final String card_id, final String s2) {

        Call<DataMap> call=apiCall.Login(card_id,s2);
        call.enqueue(new Callback<DataMap>() {
            @Override
            public void onResponse(Call<DataMap> call, Response<DataMap> response) {
                String res=response.body().getResponse();
                String name=response.body().getName();
                if(res.equals("not exist")){
                    Toast.makeText(getContext(), "Autherisation Failed,Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
                if(res.equals("exist")){
                    new PreConfig(getContext()).writeLoginStatus(true);
                    new PreConfig(getContext()).writeCardId(card_id);
                    new PreConfig(getContext()).writeName(name);
                    startActivity(new Intent(getActivity(),MainActivity.class));
                    getActivity().finish();
                    Toast.makeText(getActivity(), "LoginFragment Success..", Toast.LENGTH_SHORT).show();
                }
                mProg.dismiss();
                getActivity().finish();
            }

            @Override
            public void onFailure(Call<DataMap> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong make sure\nYou have running internet connection", Toast.LENGTH_SHORT).show();
                mProg.dismiss();
            }
        });
    }


}
