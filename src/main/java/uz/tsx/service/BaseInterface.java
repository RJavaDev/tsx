package uz.tsx.service;

import java.util.List;

public interface BaseInterface <R>{

    R getById(Long id);

    List<R> getAll();

    void delete(Long id);
}
