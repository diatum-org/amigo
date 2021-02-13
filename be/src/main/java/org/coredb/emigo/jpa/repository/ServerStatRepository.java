package org.coredb.emigo.jpa.repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;
import java.util.Set;

import org.coredb.emigo.jpa.entity.ServerStat;

public interface ServerStatRepository extends JpaRepository<ServerStat, Integer> {
  List<ServerStat> findByTimestampLessThanEqualOrderByTimestampDesc(Integer start);
  List<ServerStat> findByTimestampGreaterThanEqualOrderByTimestampDesc(Integer end);
  List<ServerStat> findByTimestampLessThanEqualAndTimestampGreaterThanEqualOrderByTimestampDesc(Integer start, Integer end);

  List<ServerStat> findAllByOrderByTimestampDesc();
  Long deleteByTimestampLessThanEqual(Integer ts);
}


