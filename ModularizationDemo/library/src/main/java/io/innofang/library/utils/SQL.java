package io.innofang.library.utils;

/**
 * Created by Inno Fang on 2017/5/31.
 */
public class SQL {

    private static final String PRIMARY_KEY = " primary key ";
    private static final String AUTOINCREMENT = " autoincrement ";
    private static final String LEFT_BRACKET = "(";
    private static final String RIGHT_BRACKET = ")";
    private static final String COMMA = ", ";
    private static final String INTEGER = " integer ";
    private static final String VARCHAR = " varchar(10) ";
    private static final String FLOAT = " float ";
    private static final String DOUBLE = " double ";
    private static final String CHAR = " char(10) ";
    private static final String TEXT = " text ";
    private static StringBuilder mSql ;

    public static SQLBuilder createTable(String tableName) {
        return new SQLBuilder(tableName);
    }

    public static class SQLBuilder {

        public SQLBuilder(String tableName) {
            mSql = new StringBuilder();
            mSql.append("create table if not exists ")
                    .append(tableName)
                    .append(LEFT_BRACKET);
        }

        public SQLBuilder addIntegerCols(String cols) {
            checkComma();
            mSql.append(cols)
                    .append(INTEGER);
            return this;
        }

        public SQLBuilder addIntegerColsWithPrimaryKey(String cols, boolean useAutoincrement) {
            checkComma();
            mSql.append(cols)
                    .append(INTEGER)
                    .append(PRIMARY_KEY);
            checkAutoincrement(useAutoincrement);
            return this;
        }

        public SQLBuilder addVarCharCols(String cols) {
            checkComma();
            mSql.append(cols)
                    .append(VARCHAR);
            return this;
        }

        public SQLBuilder addVarCharCols(String cols, int length) {
            checkComma();
            mSql.append(cols)
                    .append(" varchar(" + length + ") ");
            return this;
        }

        public SQLBuilder addVarCharColsWithPrimaryKey(String cols) {
            checkComma();
            mSql.append(cols)
                    .append(VARCHAR)
                    .append(PRIMARY_KEY);
            return this;
        }

        public SQLBuilder addVarCharColsWithPrimaryKey(String cols, int length) {
            checkComma();
            mSql.append(cols)
                    .append(" varchar(" + length + ") ")
                    .append(PRIMARY_KEY);
            return this;
        }


        public SQLBuilder addCharCols(String cols) {
            checkComma();
            mSql.append(cols)
                    .append(CHAR);
            return this;
        }

        public SQLBuilder addCharCols(String cols, int length) {
            checkComma();
            mSql.append(cols)
                    .append(" varchar(" + length + ") ");
            return this;
        }


        public SQLBuilder addCharColsWithPrimaryKey(String cols) {
            checkComma();
            mSql.append(cols)
                    .append(CHAR)
                    .append(PRIMARY_KEY);
            return this;
        }

        public SQLBuilder addCharColsWithPrimaryKey(String cols, int length) {
            checkComma();
            mSql.append(cols)
                    .append(" char(" + length + ") ")
                    .append(PRIMARY_KEY);
            return this;
        }


        public SQLBuilder addFloatCols(String cols) {
            checkComma();
            mSql.append(cols)
                    .append(FLOAT);
            return this;
        }

        public SQLBuilder addFloatColsWithPrimaryKey(String cols) {
            checkComma();
            mSql.append(cols)
                    .append(FLOAT)
                    .append(PRIMARY_KEY);
            return this;
        }


        public SQLBuilder addDoubleCols(String cols) {
            checkComma();
            mSql.append(cols)
                    .append(DOUBLE);
            return this;
        }

        public SQLBuilder addDoubleColsWithPrimaryKey(String cols) {
            checkComma();
            mSql.append(cols)
                    .append(DOUBLE)
                    .append(PRIMARY_KEY);
            return this;
        }

        public SQLBuilder addTextCols(String cols) {
            checkComma();
            mSql.append(cols)
                    .append(TEXT);
            return this;
        }

        public SQLBuilder addTextColsWithPrimaryKey(String cols) {
            checkComma();
            mSql.append(cols)
                    .append(TEXT)
                    .append(PRIMARY_KEY);
            return this;
        }


        public String create() {
            return mSql.append(RIGHT_BRACKET)
                    .toString()
                    .replace("  ", " ");
        }
    }

    private static void checkComma() {
        if (mSql.charAt(mSql.length() - 1)
                != LEFT_BRACKET.charAt(0)) {
            mSql.append(COMMA);
        }
    }

    private static void checkAutoincrement(boolean useAutoincrement) {
        if (useAutoincrement)
            mSql.append(AUTOINCREMENT);
    }

}
