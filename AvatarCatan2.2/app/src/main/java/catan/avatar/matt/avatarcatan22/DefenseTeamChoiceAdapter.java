package catan.avatar.matt.avatarcatan22;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.List;

public class DefenseTeamChoiceAdapter extends ArrayAdapter<Unit> {
    Context ctx;
    private List<Unit> unitList;
    public DefenseTeamChoiceAdapter(Context context, int resource, List<Unit> unitList) {
        super(context, resource, unitList);
        ctx = context;
        this.unitList = unitList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.list_unit, parent,false);
        }
        Unit unit = unitList.get(position);
        TextView nameUnit = (TextView) convertView.findViewById(R.id.textView11);
        nameUnit.setText(unit.getName());
        ImageView unitPic = (ImageView) convertView.findViewById(R.id.imageView2);
        int resource = ctx.getResources().getIdentifier(unit.getImage(),"drawable",ctx.getPackageName());
        unitPic.setImageResource(resource);
        return convertView;
    }
}
