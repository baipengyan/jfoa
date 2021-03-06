/*
 * Copyright (c) 2020, JavaFamily Technology Corp, All Rights Reserved.
 *
 * The software and information contained herein are copyrighted and
 * proprietary to JavaFamily Technology Corp. This software is furnished
 * pursuant to a written license agreement and may be used, copied,
 * transmitted, and stored only in accordance with the terms of such
 * license and with the inclusion of the above copyright notice. Please
 * refer to the file "COPYRIGHT" for further copyright and licensing
 * information. This software and information or any other copies
 * thereof may not be provided or otherwise made available to any other
 * person.
 */

package club.javafamily.runner.common.table.lens;

import club.javafamily.runner.common.table.cell.Cell;

/**
 * TableLens interface
 */
public interface TableLens {

   /**
    * get cell
    * @param row
    * @param col
    * @return
    */
   Cell getObject(int row, int col);

   void setObject(int row, int col, Cell cell);

   int getRowCount();

   int getColCount();

   String getTableName();

   String getDescription();

   default void reset() {
      // no op
   }

   default int getHeaderRowCount() {
      return 1;
   }

   default int getHeaderColCount() {
      return 0;
   }

}
