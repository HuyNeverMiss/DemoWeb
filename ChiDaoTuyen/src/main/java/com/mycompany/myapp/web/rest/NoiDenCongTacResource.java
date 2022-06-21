package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.NoiDenCongTac;
import com.mycompany.myapp.repository.NoiDenCongTacRepository;
import com.mycompany.myapp.service.NoiDenCongTacQueryService;
import com.mycompany.myapp.service.NoiDenCongTacService;
import com.mycompany.myapp.service.criteria.NoiDenCongTacCriteria;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.NoiDenCongTac}.
 */
@RestController
@RequestMapping("/api")
public class NoiDenCongTacResource {

    private final Logger log = LoggerFactory.getLogger(NoiDenCongTacResource.class);

    private static final String ENTITY_NAME = "noiDenCongTac";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NoiDenCongTacService noiDenCongTacService;

    private final NoiDenCongTacRepository noiDenCongTacRepository;

    private final NoiDenCongTacQueryService noiDenCongTacQueryService;

    public NoiDenCongTacResource(
        NoiDenCongTacService noiDenCongTacService,
        NoiDenCongTacRepository noiDenCongTacRepository,
        NoiDenCongTacQueryService noiDenCongTacQueryService
    ) {
        this.noiDenCongTacService = noiDenCongTacService;
        this.noiDenCongTacRepository = noiDenCongTacRepository;
        this.noiDenCongTacQueryService = noiDenCongTacQueryService;
    }

    /**
     * {@code POST  /noi-den-cong-tacs} : Create a new noiDenCongTac.
     *
     * @param noiDenCongTac the noiDenCongTac to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new noiDenCongTac, or with status {@code 400 (Bad Request)} if the noiDenCongTac has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/noi-den-cong-tacs")
    public ResponseEntity<NoiDenCongTac> createNoiDenCongTac(@RequestBody NoiDenCongTac noiDenCongTac) throws URISyntaxException {
        log.debug("REST request to save NoiDenCongTac : {}", noiDenCongTac);
        if (noiDenCongTac.getId() != null) {
            throw new BadRequestAlertException("A new noiDenCongTac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoiDenCongTac result = noiDenCongTacService.save(noiDenCongTac);
        return ResponseEntity
            .created(new URI("/api/noi-den-cong-tacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /noi-den-cong-tacs/:id} : Updates an existing noiDenCongTac.
     *
     * @param id the id of the noiDenCongTac to save.
     * @param noiDenCongTac the noiDenCongTac to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noiDenCongTac,
     * or with status {@code 400 (Bad Request)} if the noiDenCongTac is not valid,
     * or with status {@code 500 (Internal Server Error)} if the noiDenCongTac couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/noi-den-cong-tacs/{id}")
    public ResponseEntity<NoiDenCongTac> updateNoiDenCongTac(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NoiDenCongTac noiDenCongTac
    ) throws URISyntaxException {
        log.debug("REST request to update NoiDenCongTac : {}, {}", id, noiDenCongTac);
        if (noiDenCongTac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, noiDenCongTac.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noiDenCongTacRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NoiDenCongTac result = noiDenCongTacService.update(noiDenCongTac);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, noiDenCongTac.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /noi-den-cong-tacs/:id} : Partial updates given fields of an existing noiDenCongTac, field will ignore if it is null
     *
     * @param id the id of the noiDenCongTac to save.
     * @param noiDenCongTac the noiDenCongTac to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noiDenCongTac,
     * or with status {@code 400 (Bad Request)} if the noiDenCongTac is not valid,
     * or with status {@code 404 (Not Found)} if the noiDenCongTac is not found,
     * or with status {@code 500 (Internal Server Error)} if the noiDenCongTac couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/noi-den-cong-tacs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NoiDenCongTac> partialUpdateNoiDenCongTac(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NoiDenCongTac noiDenCongTac
    ) throws URISyntaxException {
        log.debug("REST request to partial update NoiDenCongTac partially : {}, {}", id, noiDenCongTac);
        if (noiDenCongTac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, noiDenCongTac.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noiDenCongTacRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NoiDenCongTac> result = noiDenCongTacService.partialUpdate(noiDenCongTac);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, noiDenCongTac.getId().toString())
        );
    }

    /**
     * {@code GET  /noi-den-cong-tacs} : get all the noiDenCongTacs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of noiDenCongTacs in body.
     */
    @GetMapping("/noi-den-cong-tacs")
    public ResponseEntity<List<NoiDenCongTac>> getAllNoiDenCongTacs(
        NoiDenCongTacCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get NoiDenCongTacs by criteria: {}", criteria);
        Page<NoiDenCongTac> page = noiDenCongTacQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /noi-den-cong-tacs/count} : count all the noiDenCongTacs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/noi-den-cong-tacs/count")
    public ResponseEntity<Long> countNoiDenCongTacs(NoiDenCongTacCriteria criteria) {
        log.debug("REST request to count NoiDenCongTacs by criteria: {}", criteria);
        return ResponseEntity.ok().body(noiDenCongTacQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /noi-den-cong-tacs/:id} : get the "id" noiDenCongTac.
     *
     * @param id the id of the noiDenCongTac to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the noiDenCongTac, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/noi-den-cong-tacs/{id}")
    public ResponseEntity<NoiDenCongTac> getNoiDenCongTac(@PathVariable Long id) {
        log.debug("REST request to get NoiDenCongTac : {}", id);
        Optional<NoiDenCongTac> noiDenCongTac = noiDenCongTacService.findOne(id);
        return ResponseUtil.wrapOrNotFound(noiDenCongTac);
    }

    /**
     * {@code DELETE  /noi-den-cong-tacs/:id} : delete the "id" noiDenCongTac.
     *
     * @param id the id of the noiDenCongTac to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/noi-den-cong-tacs/{id}")
    public ResponseEntity<Void> deleteNoiDenCongTac(@PathVariable Long id) {
        log.debug("REST request to delete NoiDenCongTac : {}", id);
        noiDenCongTacService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
