package com.HNX.dto;

import com.HNX.entity.Setmeal;
import com.HNX.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
