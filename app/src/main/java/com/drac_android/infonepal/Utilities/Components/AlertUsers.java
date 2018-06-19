package com.drac_android.infonepal.Utilities.Components;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.drac_android.infonepal.R;

public class AlertUsers {
    public interface WarningResponse{
        public void onPositive();
        public void onNegative();
    }
    public static void showWarning(Context c, String message, String positiveBtnText, final WarningResponse responseListener){

        final AlertDialog.Builder builder = new AlertDialog.Builder(c);
        View v = View.inflate(c, R.layout.warning_message_layout,null);
        builder.setView(v);
        ((TextView)v.findViewById(R.id.message)).setText(message);

        builder.setPositiveButton(positiveBtnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(responseListener!=null){
                    responseListener.onPositive();
                }
            }
        });

        final AlertDialog dialog =  builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ((ImageView)v.findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public static void showInfo(Context c, String message, String positiveBtnText, final WarningResponse responseListener){

        final AlertDialog.Builder builder = new AlertDialog.Builder(c);
        View v = View.inflate(c,R.layout.info_message_layout,null);
        builder.setView(v);
        ((TextView)v.findViewById(R.id.message)).setText(message);

        builder.setPositiveButton(positiveBtnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(responseListener!=null)
                    responseListener.onPositive();
            }
        });

        final AlertDialog dialog =  builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ((ImageView)v.findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(responseListener!=null)
                    responseListener.onNegative();
            }
        });
        dialog.show();
    }



    public static void showError(Context c, String message, String positiveBtnText, final WarningResponse responseListener){

        final AlertDialog.Builder builder = new AlertDialog.Builder(c);
        View v = View.inflate(c,R.layout.error_message_layout,null);
        builder.setView(v);
        ((TextView)v.findViewById(R.id.message)).setText(message);

        builder.setPositiveButton(positiveBtnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(responseListener!=null){
                    responseListener.onPositive();
                }
            }
        });

        final AlertDialog dialog =  builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ((ImageView)v.findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        playErrorSound(c);
    }


    public static void showSuccess(Context c, String message){
        Toast t = new Toast(c);
        View tV = View.inflate(c, R.layout.success_message_layout,null);
        ((TextView)tV.findViewById(R.id.message)).setText(message);
        t.setView(tV);
        t.setGravity(Gravity.TOP,0,100);
        t.show();
    }

    public static void showInspection(Context c, String message, String positiveBtnText,String negativeBtnText ,final WarningResponse responseListener){

        final AlertDialog.Builder builder = new AlertDialog.Builder(c);
        View v = View.inflate(c,R.layout.inspection_message_layout,null);
        builder.setView(v);
        ((TextView)v.findViewById(R.id.message)).setText(message);

        builder.setNegativeButton(negativeBtnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(responseListener!=null)
                    responseListener.onNegative();
            }
        });

        builder.setPositiveButton(positiveBtnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(responseListener!=null)
                    responseListener.onPositive();
            }
        });
        final AlertDialog dialog =  builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ((ImageView)v.findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(responseListener!=null)
                    responseListener.onNegative();
            }
        });
        dialog.show();
    }

    private static void playErrorSound(Context c) {
        MediaPlayer notifMP = MediaPlayer.create(c, R.raw.error);
        notifMP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mp != null) {
                    mp.release();
                    mp = null;
                }
            }
        });

        notifMP.start();
    }


}
