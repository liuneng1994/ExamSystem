package com.adrian.command;

/**
 * Basic pagination informations.
 */
public class BasePagination {
    private int offset;
    private int perpageCount;
    private int sortMethod;
    private String sortColumn;
    private String keyword = "";
    private int totalCount;
    private int totalPageCount;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPerpageCount() {
        return perpageCount;
    }

    public void setPerpageCount(int perpageCount) {
        this.perpageCount = perpageCount;
    }

    public int getSortMethod() {
        return sortMethod;
    }

    public void setSortMethod(int sortMethod) {
        this.sortMethod = sortMethod;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        if (checked()) {
            totalPageCount = (totalCount - 1) / perpageCount + 1;
        }
    }

    public int getTotalPageCount() {
        if (checked()) {
            totalPageCount = (totalCount - 1) / perpageCount + 1;
        }
        return totalPageCount;
    }

    /**
     * Checks pagination information's validity.
     *
     * @return true, if legal, or false, if illegal
     */
    public boolean checked() {
        boolean status = false;
        status = offset > totalCount ? false : true;
        if (status) {
            status = sortColumn == null ? false : true;
        }

        if (status) {
            status = perpageCount == 0 ? false : true;
        }
        return status;
    }
}
