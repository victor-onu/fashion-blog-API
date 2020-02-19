//package com.victor.fashionblog.search;
//
//import com.victor.fashionblog.model.Post;
//import org.springframework.data.jpa.domain.Specification;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class PostSpecificationsBuilder {
//
//    private final List<SearchCriteria> params;
//
//    public PostSpecificationsBuilder() {
//        params = new ArrayList<SearchCriteria>();
//    }
//
//    public PostSpecificationsBuilder with(String key, String operation, Object value) {
//        params.add(new SearchCriteria(key, operation, value));
//        return this;
//    }
//
//    public Specification<Post> build() {
//        if (params.size() == 0) {
//            return null;
//        }
//
//        List<Specification> specs = params.stream()
//                .map(PostSpecification::new)
//                .collect(Collectors.toList());
//
//        Specification result = specs.get(0);
//
//        for (int i = 1; i < params.size(); i++) {
//            result = params.get(i)
//                    .isOrPredicate()
//                    ? Specification.where(result)
//                    .or(specs.get(i))
//                    : Specification.where(result)
//                    .and(specs.get(i));
//        }
//        return result;
//    }
//}
