package com.example.sahil.try3;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter{

    String [] result;
    Context context;
    single dbAccess = single.getInstance();
    int [] imageId;
    private static LayoutInflater inflater=null;

    public CustomAdapter(Main2Activity mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.program_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);

        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String item = null;
                String itemrate = null;
                int i = 0;
                while (i < dbAccess.datahub.itemslist.size()) {
                    if (dbAccess.datahub.itemslist.get(i).equalsIgnoreCase(result[position])) {
                        item = dbAccess.datahub.itemslist.get(i);
                        itemrate = dbAccess.datahub.itemsrates.get(i);
                        Toast.makeText(context, "" + result[position] + " Costs " + itemrate, Toast.LENGTH_LONG).show();
                        break;
                    }
                    i++;

                }




            }
        });

        return rowView;
    }

}
