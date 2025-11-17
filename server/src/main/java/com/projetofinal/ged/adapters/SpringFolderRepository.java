package com.projetofinal.ged.adapters;


import com.projetofinal.ged.domain.FolderFileKpis;
import com.projetofinal.ged.infra.entities.JPAFolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringFolderRepository extends JpaRepository<JPAFolderEntity, UUID> {

    List<JPAFolderEntity> findAllByProjectIdOrderByTitleAsc(UUID id);

    List<JPAFolderEntity> findAllByOrderByTitleAsc();

    @Query(value = """
            SELECT
                LOWER(SPLIT_PART(a.nome_do_arquivo, '.', 2)) AS type,
                COUNT(*) AS quantity
            FROM arquivo a
            WHERE nome_do_arquivo LIKE '%.%'
            and pasta_id = :id
            GROUP BY type
            HAVING LOWER(SPLIT_PART(a.nome_do_arquivo, '.', 2)) <> ''
            """, nativeQuery = true)
    List<Object[]> fileKpis(UUID id);

    @Query(value = """
            SELECT concat(to_char(data_de_upload, 'Mon'), '/', extract(YEAR FROM data_de_upload)) AS PERIOD, count(*) AS quantity
            FROM arquivo a\s
            WHERE data_de_upload IS NOT NULL
            and pasta_id = :id
            GROUP BY PERIOD;
            """, nativeQuery = true)
    List<Object[]> kpisByPeriod(UUID id);
}
