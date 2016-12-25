package catan.avatar.matt.avatarcatan22;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.ls.LSException;

import java.util.HashMap;
import java.util.List;

public class AdapterUnitExpandList extends BaseExpandableListAdapter {
    private Context ctx;
    private List<Unit> listOfHeroes;
    public AdapterUnitExpandList(Context ctx,
                                 List<Unit> listOfHeroes
    ) {
        this.ctx = ctx;
        this.listOfHeroes = listOfHeroes;
    }

    @Override
    public int getGroupCount() {
        return listOfHeroes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listOfHeroes.get(groupPosition).getName();
    }

    @Override
    public Object getChild(int parent, int child) {
        return listOfHeroes.get(parent).getStats();
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
        String goldValue = (listOfHeroes.get(groupPosition).getGold() + " Gold");
        String pic = listOfHeroes.get(groupPosition).getImage();

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
        String unitStats =  (String) getChild(parent, child);
        if(convertview == null)
        {
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflator.inflate(R.layout.expanded_list_child, parentView,false);
        }
        TextView child_textview = (TextView) convertview.findViewById(R.id.textView9);
        child_textview.setText(unitStats);
        return convertview;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
