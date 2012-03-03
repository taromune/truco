package org.android.truco;
import java.util.List;

import org.android.db.*;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterLista extends BaseAdapter {

    private Context mContext;
    private List<Lista> elements;

    public AdapterLista (Context mContext) {
        this.mContext = mContext;
        ListaDB l = new ListaDB(mContext);
        elements =l.getListas();
    }

public int getCount() {
        return elements.size();
}

public Lista getItem(int position) {
        return (Lista)elements.get(position);
}

public long getItemId(int position) {
		return this.elements.get(position).getId();
}

public View getView(int position, View convertView, ViewGroup parent) {
        Lista item = elements.get(position);

        View v = View.inflate(mContext,R.layout.fila, null);

        TextView nombre = (TextView)v.findViewById(R.id.nombreLista);
        nombre.setText(item.getNombre());
        TextView notas = (TextView)v.findViewById(R.id.notaLista);
        notas.setText(item.getNota());
        return v;
}

}
