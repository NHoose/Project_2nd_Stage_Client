package de.team_eduart.project2ndstage;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MailFragment extends Fragment {

    public MailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_mail, container, false);

        final PackageManager manager = this.getActivity().getPackageManager();

        Button OpenFileButton = (Button)rootView.findViewById(R.id.BtnOpenFile);
        OpenFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFile = manager.getLaunchIntentForPackage("com.adobe.reader");
                startActivity(openFile);
            }
        });

        return rootView;
    }

}
