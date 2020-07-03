package com.aubga.java.serializable;


import java.util.List;

/*import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;*/

/**
 * USER:    huangyunxing
 * TIME:    2018-06-12 19:30
 * COMMENT:
 */
/*@Data
@NoArgsConstructor
@ToString(callSuper = true, includeFieldNames = true)*/
public class User {
	private String userName;
	private String PassWord;
	private String userInfo;
	private List<User> friends;
}
