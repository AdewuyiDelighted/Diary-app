package gossipme.exceptions;

public class DiaryLockedException extends DiaryAppException {
    public DiaryLockedException(){
        super("Diary locked");
    }
}
