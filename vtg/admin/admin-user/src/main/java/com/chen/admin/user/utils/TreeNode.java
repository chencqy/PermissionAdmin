package com.chen.admin.user.utils;

import com.chen.admin.user.entity.vo.MenuVo;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author : Chen
 * @Date : Create in 2019/1/24 19:33
 * @Description :
 * @Modify by :
 */
public class TreeNode {

    /**
     * 找到parent为0的根节点，并递归寻找他的子节点
     * @param nodes
     * @return
     */
    public static List<MenuVo> findRoot(List<MenuVo> nodes) {
        List<MenuVo> root = new ArrayList<>();
        nodes.forEach(node -> {
            if (node.getParent() == 0) {
                root.add(node);
            }
        });
        root.forEach(node -> findChildren(node, nodes));
        return root;
    }

    /**
     * 递归寻找子节点
     * @param root
     * @param nodes
     * @return
     */
    public static MenuVo findChildren(MenuVo root, List<MenuVo> nodes) {
        nodes.forEach(node -> {
            if (node.getParent().equals(root.getId())) {
                if (root.getChildren() == null) {
                    root.setChildren(new ArrayList<>());
                }
                root.getChildren().add(findChildren(node, nodes));
            }
        });
        return root;
    }

}
