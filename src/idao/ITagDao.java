package idao;

import java.util.List;

public interface ITagDao {

    // 删除标签时(id,tag) 若仅有某一个属性
    // 可以使用这些default值表示在sql语句中跳过这个属性
    // delete from t_tag where id=? or tag=?
    int DEFAULT_ID = 0;
    String DEFAULT_TAG = "LEMONREDS2017";


    // 新的标签

    boolean addTag(int id, String tag);

    // 删除标签
    boolean deleteTag(int id, String tag);


    //获取所有的标签 不含重复

    List getAllTag();

    /**
     * 更新标签
     *
     * @param old_tag
     * @param new_tag
     * @return
     */
    boolean updateTag(String old_tag, String new_tag);

    /**
     * 通过列属性获取标签
     *
     * @param column
     * @param value
     * @return
     */
    List getTagByColumn(String column, String value);



}