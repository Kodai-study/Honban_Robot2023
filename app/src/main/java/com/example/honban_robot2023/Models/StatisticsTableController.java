package com.example.honban_robot2023.Models;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.honban_robot2023.APIModules.StatisticsAPIModel;

import java.util.Date;

public class StatisticsTableController extends TableResultControl<StatisticsAPIModel> {

    public StatisticsTableController(Context activityContext, TableLayout tableLayout) {
        super(activityContext, tableLayout);
    }

    @Override
    public TableRow addRowFromModule(StatisticsAPIModel colum) {
        TableRow tableRow = new TableRow(this.activityContext);
        TextView[] textViewCells = new TextView[StatisticsAPIModel.COLUM_NUMBER];

        for (int i = 0; i < StatisticsAPIModel.COLUM_NUMBER; i++) {
            textViewCells[i] = new TextView(activityContext);
            setColumText(textViewCells[i]);
            tableRow.addView(textViewCells[i]);
        }
        Date firstDate = colum.getFirstDateOfRange();
        Date lastDate = colum.getEndDateOfRange();
        if (firstDate.compareTo(lastDate) == 0)
            textViewCells[0].setText(ConfigParameters.DATEONLY_FORMATTER.format(firstDate));
        else
            textViewCells[0].setText(
                    String.format("%s\n~\n%s", ConfigParameters.DATEONLY_FORMATTER.format(firstDate),
                            ConfigParameters.DATEONLY_FORMATTER.format(lastDate)));

        textViewCells[1].setText(String.valueOf(colum.getCount_Scan()));
        textViewCells[2].setText(String.valueOf(colum.getCount_Ok()));
        textViewCells[3].setText(String.valueOf(colum.getCount_Ng()));
        textViewCells[4].setText(String.valueOf(colum.getDefectRate()));
        textViewCells[5].setText(String.valueOf(colum.getNgCount_IC1()));
        textViewCells[6].setText(String.valueOf(colum.getNgCount_IC2()));
        textViewCells[7].setText(String.valueOf(colum.getNgCount_R5()));
        textViewCells[8].setText(String.valueOf(colum.getNgCount_R10()));
        textViewCells[9].setText(String.valueOf(colum.getNgCount_R11()));
        textViewCells[10].setText(String.valueOf(colum.getNgCount_R12()));
        textViewCells[11].setText(String.valueOf(colum.getNgCount_R18()));
        textViewCells[12].setText(String.valueOf(colum.getNgCount_DIPSW()));
        textViewCells[13].setText(String.valueOf(colum.getNgCount_Voltage()));
        textViewCells[14].setText(String.valueOf(colum.getNgCount_Frequency()));

        return tableRow;
    }

}