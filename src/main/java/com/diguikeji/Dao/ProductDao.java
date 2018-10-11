package com.diguikeji.Dao;

import com.diguikeji.entity.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class ProductDao {

    public ProductDao() throws IOException {

    }
    String resourse = "mybatis.xml";
    InputStream is = Resources.getResourceAsStream(resourse);
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
    SqlSession session = factory.openSession();


    public Product addProduct(Product product) throws IOException {

        try {
            session.insert("addProduct",product);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return product;
    }
}
