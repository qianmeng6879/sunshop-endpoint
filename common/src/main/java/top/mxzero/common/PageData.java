package top.mxzero.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageData<T> {
    private long current;
    private long size;
    private long total;
    private List<T> data;
}
