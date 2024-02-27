package uz.tsx.service;

import java.util.List;

public interface BaseInterface <R>{

    R getById(Integer id);

    List<R> getAll();

    void delete(Integer id);
}
