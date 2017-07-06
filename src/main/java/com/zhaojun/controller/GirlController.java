package com.zhaojun.controller;

import com.zhaojun.domain.Girl;
import com.zhaojun.domain.Result;
import com.zhaojun.repository.GirlRepository;
import com.zhaojun.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by hasee on 2017/6/30.
 */
@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    public List<Girl> getListGirl(){
        return girlRepository.findAll();
    }

    /**
     * 添加一条数据
     * @param girl
     * @return
     */
    @PostMapping("/add")
    public Result addGirl(@Valid  Girl girl, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
                return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
            }
            girl.setName(girl.getName());
            girl.setAge(girl.getAge());

            girlRepository.save(girl);
            return ResultUtil.success(girl);
    }

    /**
     * 查找单条数据
     * @param id
     * @return
     */
    @GetMapping("/girls/{id}")
    public  Girl finGirlById(@PathVariable("id") Integer id){
        Girl girl = girlRepository.findOne(id);
        return girl;
    }

    /**
     * 修改一条数据
     * @param id
     * @param name
     * @param age
     * @return
     */
    @PutMapping("/girls/{id}")
    public  Girl girlUpdate(@PathVariable("id") Integer id,@RequestParam("name") String name,@RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setName(name);
        girl.setAge(age);
        return girlRepository.save(girl);
    }

    /**
     * 删除一条数据
     * @param id
     */
    @DeleteMapping("/girls/{id}")
    public void delete(@PathVariable("id") Integer id){
        girlRepository.delete(id);
    }

}
