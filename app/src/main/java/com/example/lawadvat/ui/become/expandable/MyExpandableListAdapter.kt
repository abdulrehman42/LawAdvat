package com.example.lawadvat.ui.become.expandable

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.lawadvat.R

class MyExpandableListAdapter(
    private val context: Context,
    private val expandableListTitle: List<String>,
    private val expandableListDetail: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return expandableListDetail[expandableListTitle[groupPosition]]?.get(childPosition)!!
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    @SuppressLint("MissingInflatedId")
    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val childText = getChild(groupPosition, childPosition) as String
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val convertView = inflater.inflate(R.layout.list_item, null)
        val txtListChild = convertView.findViewById<TextView>(R.id.lblListItem)
        txtListChild.text = childText
        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return expandableListDetail[expandableListTitle[groupPosition]]?.size ?: 0
    }

    override fun getGroup(groupPosition: Int): Any {
        return expandableListTitle[groupPosition]
    }

    override fun getGroupCount(): Int {
        return expandableListTitle.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val listTitle = getGroup(groupPosition) as String
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val convertView = inflater.inflate(R.layout.list_group, null)
        val lblListHeader = convertView.findViewById<TextView>(R.id.lblListHeader)
        lblListHeader.text = listTitle
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}