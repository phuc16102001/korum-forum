package vn.elca.training.repository.custom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.model.constant.Constant;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.QEmployee;
import vn.elca.training.model.entity.QProject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

public class ProjectRepositoryImpl implements ProjectRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Project findProjectById(Long projectId) {
        return new JPAQuery<Project>(em)
                .from(QProject.project)
                .leftJoin(QProject.project.employees, QEmployee.employee)
                .fetchJoin()
                .where(QProject.project.id.eq(projectId))
                .fetchFirst();
    }

    @Override
    public List<Project> getProjectsByCriteriaOrStatus(String criteria, String status) {
        BooleanExpression query = QProject.project.id.isNotNull();

        if (ObjectUtils.isNotEmpty(criteria)) {
            try {
                query = (QProject.project.projectNumber.eq(Long.valueOf(criteria))
                        .or(QProject.project.name.containsIgnoreCase(criteria)));
            } catch (NumberFormatException e) {
                query = QProject.project.name.containsIgnoreCase(criteria)
                        .or(QProject.project.customer.containsIgnoreCase(criteria));
            }
        }

        if (ObjectUtils.isNotEmpty(status) && Constant.PROJECT_STATUS.containsValue(status)) {
            for (Map.Entry<String, String> entry : Constant.PROJECT_STATUS.entrySet()) {
                if (status.equals(entry.getValue())) {
                    status = entry.getKey();
                    break;
                }
            }
            query = query.and(QProject.project.status.eq(Project.Status.valueOf(status)));
        }

        return new JPAQuery<Project>(em)
                .from(QProject.project)
                .where(query)
                .orderBy(QProject.project.projectNumber.asc())
                .fetch();
    }
}

