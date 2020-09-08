package com.rajaryan.searchinrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {
    EditText search_text;
    RecyclerView recyclerView;
    Adapter adapter;
    Query query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_text=findViewById(R.id.search_text);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String search=search_text.getText().toString();
                Query SearchQuery= FirebaseDatabase.getInstance().getReference().child("Talks").child("Videos").orderByChild("tag").startAt(search.toLowerCase()).endAt(search.toLowerCase() + "\uf8ff");
                FirebaseRecyclerOptions<VideoWithTag> options =
                        new FirebaseRecyclerOptions.Builder<VideoWithTag>()
                                .setQuery(SearchQuery,VideoWithTag.class)
                                .build();
                adapter=new Adapter(options);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String search=search_text.getText().toString();
                Query SearchQuery= FirebaseDatabase.getInstance().getReference().child("Talks").child("Videos").orderByChild("tag").startAt(search.toLowerCase()).endAt(search.toLowerCase() + "\uf8ff");
                FirebaseRecyclerOptions<VideoWithTag> options =
                        new FirebaseRecyclerOptions.Builder<VideoWithTag>()
                                .setQuery(SearchQuery,VideoWithTag.class)
                                .build();
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter=new Adapter(options);
                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }
        });
        query= FirebaseDatabase.getInstance().getReference().child("Talks").child("Videos");
        FirebaseRecyclerOptions<VideoWithTag> options =
                new FirebaseRecyclerOptions.Builder<VideoWithTag>()
                        .setQuery(query,VideoWithTag.class)
                        .build();
        adapter=new Adapter(options);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    //Creating Adapter For Recyclerview
    public class Adapter extends FirebaseRecyclerAdapter<VideoWithTag,Adapter.viewholder> {

        /**
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */
        public Adapter(@NonNull FirebaseRecyclerOptions<VideoWithTag> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull Adapter.viewholder viewholder, int i, @NonNull VideoWithTag videoWithTag) {
            try {
                Glide.with(getApplicationContext()).load(videoWithTag.image).into(viewholder.image);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @NonNull
        @Override
        public Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.image_card, parent, false);
            return new viewholder(view);
        }

        public class viewholder extends RecyclerView.ViewHolder{
            ImageView image;
            public viewholder(@NonNull View itemView) {
                super(itemView);
                image=itemView.findViewById(R.id.imageView2);
            }
        }
    }
    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
