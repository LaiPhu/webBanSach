package com.example.webSpring.api.config;

import com.example.webSpring.api.model.user.User;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.AbstractContextMapper;
public class PersonContextMapper  extends AbstractContextMapper<User> {

    @Override
    protected User doMapFromContext(DirContextOperations ctx) {
        User person = new User();
        person.setId(ctx.getStringAttribute("id")); // Sử dụng phương thức getStringAttribute() để lấy giá trị thuộc tính từ Context
        person.setName(ctx.getStringAttribute("name"));
        person.setAddress(ctx.getStringAttribute("address"));
        person.setPhoneNumber(ctx.getStringAttribute("phone_number"));
        person.setDate(Long.valueOf(ctx.getStringAttribute("date")));
        person.setPassWord(ctx.getStringAttribute("pass_word"));
        person.setStatus(Integer.valueOf(ctx.getStringAttribute("status")));
        person.setPermission(Integer.valueOf(ctx.getStringAttribute("permission")));
        return person;
    }

    public void mapToContext(Object object, DirContextAdapter ctx) {
        User person = (User) object;
        ctx.setAttributeValue("id", person.getId()); // Sử dụng phương thức setAttributeValue() để đặt giá trị thuộc tính vào Context
        ctx.setAttributeValue("name", person.getName());
        ctx.setAttributeValue("address", person.getAddress());
        ctx.setAttributeValue("phone_number", person.getPhoneNumber());
        ctx.setAttributeValue("date", person.getDate().toString());
        ctx.setAttributeValue("pass_word", person.getPassWord());
        ctx.setAttributeValue("status", person.getStatus().toString());
        ctx.setAttributeValue("permission", person.getPermission().toString());
    }
}
