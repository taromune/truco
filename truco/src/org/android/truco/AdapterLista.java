package org.android.truco;
import java.util.List;

import org.android.db.Lista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterLista extends BaseAdapter {
        
        // private objects
        private List<Lista> mLista;
        private LayoutInflater mInflater;
        
        /*
         * constructor
         */
        public AdapterLista(Context c, List<Lista> list) {        
                mLista = list;           
                // create layout inflater
                mInflater = LayoutInflater.from(c);
        }
        
        /*
         * (non-Javadoc)
         * @see android.widget.Adapter#getCount()
         */
        public int getCount() {
                return mLista.size();
        }

        /*
         * (non-Javadoc)
         * @see android.widget.Adapter#getItem(int)
         */
        public Object getItem(int position) {
                return mLista.get(position);
        }

        /*
         * (non-Javadoc)
         * @see android.widget.Adapter#getItemId(int)
         */
        public long getItemId(int position) {
                return position;
        }

        /*
         * (non-Javadoc)
         * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
         */
        public View getView(int position, View convertView, ViewGroup parent) {
                // get view reference
                View view = convertView;
                // if null 
                if(view == null) {
                        // inflate new layout
                        view = mInflater.inflate(R.layout.fila, null);
                        // create a holder
                        ViewHolder holder = new ViewHolder();
                        // find controls
                        holder.nombreLista = (TextView)view.findViewById(R.id.nombreLista);
                        holder.notaLista = (TextView)view.findViewById(R.id.notaLista);
                        // set data structure to view
                        view.setTag(holder);
                }
                
                // get selected user info
                Lista Lista = mLista.get(position);
                // if not null
                if(Lista != null) {
                        // query data structure
                        ViewHolder holder = (ViewHolder)view.getTag();
                        // set data to display
                        holder.nombreLista.setText(Lista.getNombre());
                        holder.notaLista.setText(Lista.getNota());
                }               
                
                // return view
                return view;
        }

        /*
         * @class ViewHolder
         * to hold data structure on view with user info
         */
        static class ViewHolder {
                private TextView nombreLista;
                private TextView notaLista;
                //TODO: cantidad de productos
        }
}