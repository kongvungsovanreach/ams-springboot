package com.kshrd.btb.holymomo.utility;

public class Paging {
    private int currentPage;
    private int limit;
    private int offset;
    private int totalRecord;
    private int leftRightPageToShow;
    private int startShowPage;
    private int endShowPage;

    public Paging(int totalRecord) {
        this.leftRightPageToShow =2;
        this.limit=10;
        this.currentPage=1;
        this.setTotalRecord(totalRecord);
    }

    public Paging() {
        this.leftRightPageToShow =2;
        this.limit=10;
        this.currentPage=1;
    }

    private void resetStartEndPage(){
        int totalPageNumberShow=leftRightPageToShow*2+1;
        if(getMaxPage()<totalPageNumberShow){
            startShowPage=1;
            endShowPage=getMaxPage();
        }else if(currentPage<=leftRightPageToShow){
            startShowPage=1;
            endShowPage=totalPageNumberShow;
        }else if(currentPage>getMaxPage()-leftRightPageToShow){
            endShowPage=getMaxPage();
            startShowPage=endShowPage-totalPageNumberShow+1;
        }else{
            startShowPage=currentPage-leftRightPageToShow;
            endShowPage=currentPage+leftRightPageToShow;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return (currentPage-1)*limit;
    }

    public int getMaxPage(){
        return (int)Math.ceil(totalRecord*1.0/limit);
    }

    public int getFirstPage(){
        return 1;
    }

    public int getLastPage(){
        return getMaxPage();
    }

    public int getNextPage(){
        if(currentPage<getMaxPage()){
            return currentPage+1;
        }
        return currentPage;
    }

    public int getPreviousPage(){
        if(currentPage>1){
            return currentPage-1;
        }
        return currentPage;
    }

    public int getCurrentPage() {

        return currentPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public int getStartShowPage() {
        return startShowPage;
    }

    public int getEndShowPage() {
        return endShowPage;
    }

    public void setCurrentPage(int currentPage) {

        this.currentPage = currentPage;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setTotalRecord(int totalRecord) {

        this.totalRecord = totalRecord;
        resetStartEndPage();
    }

    public void setLeftRightPageToShow(int leftRightPageToShow) {
        this.leftRightPageToShow = leftRightPageToShow;
        resetStartEndPage();

    }

    @Override
    public String toString() {
        return "Paging{" +
                "currentPage=" + currentPage +
                ", limit=" + limit +
                ", offset=" + offset +
                ", totalRecord=" + totalRecord +
                ", leftRightPageToShow=" + leftRightPageToShow +
                ", startShowPage=" + startShowPage +
                ", endShowPage=" + endShowPage +
                '}';
    }
}
