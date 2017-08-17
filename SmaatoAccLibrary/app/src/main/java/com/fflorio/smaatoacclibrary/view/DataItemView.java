package com.fflorio.smaatoacclibrary.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fflorio.smaatoacclibrary.R;
import com.fflorio.smaatoacclibrary.models.DataItem;
import com.fflorio.smaatoacclibrary.models.ImageObject;
import com.fflorio.smaatoacclibrary.models.ObjectType;
import com.fflorio.smaatoacclibrary.models.TextObject;

import java.io.IOException;
import java.net.URL;

/**
 * Created by francesco on 2017-08-17.
 */

public class DataItemView extends FrameLayout {

    private TextView text;
    private ImageView image;
    private TextView date;
    private TextView user;

    public DataItemView(@NonNull Context context) {
        super(context);
        init();
    }

    public DataItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DataItemView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DataItemView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view__data_item, this, true);
        text = findViewById(R.id.itemText);
        image = findViewById(R.id.itemImage);
        date = findViewById(R.id.itemDate);
        user = findViewById(R.id.itemUser);
    }

    public void updateWith(DataItem dataItem){
        date.setText(dataItem.getDate());
        user.setText(dataItem.user.toString());
        if(dataItem.data instanceof ImageObject){
            text.setVisibility(View.GONE);
            loadImage(((ImageObject) dataItem.data).imageUrl);
        }else if(dataItem.data instanceof TextObject){
            image.setVisibility(View.GONE);
            text.setText(((TextObject) dataItem.data).text);
        }else{
            text.setVisibility(View.GONE);
            image.setVisibility(View.GONE);
        }
    }

    private void loadImage(final String url){
        new AsyncTask<String, Void, Bitmap>(){
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if(bitmap != null){
                    image.setImageBitmap(bitmap);
                }else{
                    image.setVisibility(View.GONE);
                    Toast.makeText(getContext(), R.string.error_load_image, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected Bitmap doInBackground(String... urls) {
                try {
                    URL url = new URL(urls[0]);
                    return BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }catch (IOException ignored){
                }
                return null;
            }
        }.execute(url);
    }


    @Override
    public Parcelable onSaveInstanceState() {
        //begin boilerplate code that allows parent classes to save state
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        //end
        if(text.getVisibility() == VISIBLE){
            ss.type = ObjectType.TEXT;
            ss.messageText = text.getText().toString();
        }else if(image.getVisibility() == VISIBLE){
            ss.type = ObjectType.IMAGE;
            ss.bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        }
        ss.userText = user.getText().toString();
        ss.dateText = date.getText().toString();
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        //begin boilerplate code so parent classes can restore state
        if(!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState)state;
        super.onRestoreInstanceState(ss.getSuperState());
        //end

        if(ss.type == ObjectType.TEXT){
            image.setVisibility(View.GONE);
            text.setText(ss.messageText);
        }else if(ss.type == ObjectType.IMAGE){
            text.setVisibility(View.GONE);
            image.setImageBitmap(ss.bitmap);
        }
        user.setText(ss.userText);
        date.setText(ss.dateText);
    }

    static class SavedState extends BaseSavedState {
        ObjectType type;
        Bitmap bitmap;
        String messageText;
        String userText;
        String dateText;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.type = ObjectType.values()[in.readInt()];
            if(type == ObjectType.IMAGE) {
                this.bitmap = in.readParcelable(Bitmap.class.getClassLoader());
            }else if(type == ObjectType.TEXT) {
                this.messageText = in.readString();
            }
            this.userText = in.readString();
            this.dateText = in.readString();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(type.ordinal());
            if(type == ObjectType.IMAGE) {
                out.writeParcelable(bitmap, flags);
            }else if(type == ObjectType.TEXT) {
                out.writeString(messageText);
            }
            out.writeString(userText);
            out.writeString(dateText);

        }

        //required field that makes Parcelables from a Parcel
        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }
                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }
}
