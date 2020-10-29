package weibo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Weibo {
	String itemid;

	String content;

	boolean isvideo=false;

	public boolean isIsvideo() {
		return isvideo;
	}
	
}
