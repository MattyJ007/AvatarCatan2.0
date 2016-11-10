package catan.avatar.matt.avatarcatan22;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;

public class UnitExpandListAdapter extends BaseExpandableListAdapter {
    private Context ctx;
    private HashMap<String, List<Unit>> unitsList;
    private List<String> statsList;

    public UnitExpandListAdapter(Context ctx, HashMap<String, List<Unit>> unitsList, List<String> statsList) {
        this.ctx = ctx;
        this.unitsList = unitsList;
        this.statsList = statsList;
    }

    @Override
    public int getGroupCount() {
        return statsList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return unitsList.get(statsList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return statsList.get(groupPosition);
    }

    @Override
    public Object getChild(int parent, int child) {
        return unitsList.get(statsList.get(parent)).get(0).getStats();
    }
    public Byte getChildGold(int parent) {
        return unitsList.get(statsList.get(parent)).get(0).getGold();
    }
    public String getChildPic(int parent) {
        return unitsList.get(statsList.get(parent)).get(0).getImage();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertview, ViewGroup parentview) {
        String group_title = (String) getGroup(groupPosition);
        String goldValue = (getChildGold(groupPosition) + " Gold");
        String pic = getChildPic(groupPosition);

        if(convertview == null)
        {
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflator.inflate(R.layout.expanded_list_parent, parentview,false);
        }

        TextView parent_textview = (TextView) convertview.findViewById(R.id.textView10);
        TextView gold_text = (TextView) convertview.findViewById(R.id.textView8);
        ImageView unitPic = (ImageView) convertview.findViewById(R.id.imageView);
        int resource = ctx.getResources().getIdentifier(pic,"drawable",ctx.getPackageName());
        unitPic.setImageResource(resource);
        parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(group_title);
        gold_text.setText(goldValue);
        return convertview;
    }

    @Override
    public View getChildView(int parent, int child, boolean isLastChild, View convertview, ViewGroup parentView) {
        String child_title =  (String) getChild(parent, child);
        if(convertview == null)
        {
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflator.inflate(R.layout.expanded_list_child, parentView,false);
        }
        TextView child_textview = (TextView) convertview.findViewById(R.id.textView9);
        child_textview.setText(child_title);
        return convertview;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
