package com.example.swlee.webapp;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by swlee on 2017. 8. 29..
 */

public class  FirebaseInstanceIDService extends FirebaseInstanceIdService {



    private static final String TAG = "MyFirebaseIIDService";



    // [START refresh_token]

    @Override

    public void onTokenRefresh() {

        // Get updated InstanceID token.

        String token = FirebaseInstanceId.getInstance().getToken();



    }

}
