package org.brandroid.openmanager.ftp;

import java.util.Comparator;

import org.apache.commons.net.ftp.FTPFile;
import org.brandroid.openmanager.FileManager.SortType;

public class FTPFileComparer implements Comparator<FTPFile>
{
	public Boolean FoldersFirst = true;
	private SortType SortType;
	
	public FTPFileComparer()
	{
		SortType = SortType.ALPHA;
	}
	
	public FTPFileComparer(SortType type)
	{
		SortType = type;
	}
	
	public FTPFileComparer(SortType type, Boolean foldersFirst)
	{
		SortType = type;
		FoldersFirst = foldersFirst;
	}
	
	public int compare(FTPFile fa, FTPFile fb) {
		if(FoldersFirst)
		{
			if(fa.isDirectory() && !fb.isDirectory())
				return 0;
			else if(!fa.isDirectory() && fb.isDirectory())
				return 1;
		}
		String a = fa.getName();
		String b = fb.getName();
		Long sa = fa.getSize();
		Long sb = fb.getSize();
		switch(SortType)
		{
			case ALPHA_DESC:
				return b.toLowerCase().compareTo(a.toLowerCase());
			case ALPHA:
				return a.toLowerCase().compareTo(b.toLowerCase());
			case SIZE_DESC:
				return sb.compareTo(sa);
			case SIZE:
				return sa.compareTo(sb);
			case DATE_DESC:
				return fb.getTimestamp().compareTo(fa.getTimestamp());
			case DATE:
				return fa.getTimestamp().compareTo(fb.getTimestamp());
			case TYPE:
				String ea = a.substring(a.lastIndexOf(".") + 1, a.length()).toLowerCase();
				String eb = b.substring(b.lastIndexOf(".") + 1, b.length()).toLowerCase();
				return ea.compareTo(eb);
			case NONE:
				return 0;
			default:
				return a.toLowerCase().compareTo(b.toLowerCase());
		}
	}	
}