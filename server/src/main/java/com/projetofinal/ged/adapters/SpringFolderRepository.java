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
            GROUP BY type
            HAVING LOWER(SPLIT_PART(a.nome_do_arquivo, '.', 2)) <> ''
            """, nativeQuery = true)
    List<Object[]> kpis();
}
