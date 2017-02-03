package cn.edu.gcu.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.edu.gcu.oa.entity.Department;

/**
 * 部门工具
 * @author zch
 *
 */
public class DepartmentUtils {
	
	/**
	 * 把部门名称修改成树状结构并返回一个treeDepartmentList集合
	 * @param topList
	 * @return
	 */
	public static List<Department> getAllDepartments(List<Department> topList) {
		//把改为树状结构的部门存放到treeDepartmentList中
		List<Department> treeDepartmentList = new ArrayList<Department>();
		walkDepartmentTreeList(topList,  "┣", treeDepartmentList);
		return treeDepartmentList;
	}
	
	/**
	 * 递归,遍历每个顶级部门,修改部门名称,并把改好的对象放到treeDepartmentList中
	 * @param topList
	 * @param treeFlag
	 * @param treeDepartmentList
	 */
	private static void walkDepartmentTreeList(Collection<Department> topList, String treeFlag, List<Department> treeDepartmentList) {
		for (Department top : topList) {
			Department copy = new Department();//副本,用于复制Session中的Department
			copy.setId(top.getId());
			copy.setName(treeFlag + top.getName());
			
			treeDepartmentList.add(copy);
			
			//子树,每进入一层,增加4个空格
			walkDepartmentTreeList(top.getChildren(), "　" + treeFlag, treeDepartmentList);
		}
	}
}
