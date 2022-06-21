package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.LyDoCongTac;
import com.mycompany.myapp.repository.LyDoCongTacRepository;
import com.mycompany.myapp.service.LyDoCongTacQueryService;
import com.mycompany.myapp.service.LyDoCongTacService;
import com.mycompany.myapp.service.criteria.LyDoCongTacCriteria;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.LyDoCongTac}.
 */
@RestController
@RequestMapping("/api")
public class LyDoCongTacResource {

    private final Logger log = LoggerFactory.getLogger(LyDoCongTacResource.class);

    private static final String ENTITY_NAME = "lyDoCongTac";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LyDoCongTacService lyDoCongTacService;

    private final LyDoCongTacRepository lyDoCongTacRepository;

    private final LyDoCongTacQueryService lyDoCongTacQueryService;

    public LyDoCongTacResource(
        LyDoCongTacService lyDoCongTacService,
        LyDoCongTacRepository lyDoCongTacRepository,
        LyDoCongTacQueryService lyDoCongTacQueryService
    ) {
        this.lyDoCongTacService = lyDoCongTacService;
        this.lyDoCongTacRepository = lyDoCongTacRepository;
        this.lyDoCongTacQueryService = lyDoCongTacQueryService;
    }

    /**
     * {@code POST  /ly-do-cong-tacs} : Create a new lyDoCongTac.
     *
     * @param lyDoCongTac the lyDoCongTac to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lyDoCongTac, or with status {@code 400 (Bad Request)} if the lyDoCongTac has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ly-do-cong-tacs")
    public ResponseEntity<LyDoCongTac> createLyDoCongTac(@RequestBody LyDoCongTac lyDoCongTac) throws URISyntaxException {
        log.debug("REST request to save LyDoCongTac : {}", lyDoCongTac);
        if (lyDoCongTac.getId() != null) {
            throw new BadRequestAlertException("A new lyDoCongTac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LyDoCongTac result = lyDoCongTacService.save(lyDoCongTac);
        return ResponseEntity
            .created(new URI("/api/ly-do-cong-tacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ly-do-cong-tacs/:id} : Updates an existing lyDoCongTac.
     *
     * @param id the id of the lyDoCongTac to save.
     * @param lyDoCongTac the lyDoCongTac to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lyDoCongTac,
     * or with status {@code 400 (Bad Request)} if the lyDoCongTac is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lyDoCongTac couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ly-do-cong-tacs/{id}")
    public ResponseEntity<LyDoCongTac> updateLyDoCongTac(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LyDoCongTac lyDoCongTac
    ) throws URISyntaxException {
        log.debug("REST request to update LyDoCongTac : {}, {}", id, lyDoCongTac);
        if (lyDoCongTac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lyDoCongTac.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lyDoCongTacRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LyDoCongTac result = lyDoCongTacService.update(lyDoCongTac);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lyDoCongTac.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ly-do-cong-tacs/:id} : Partial updates given fields of an existing lyDoCongTac, field will ignore if it is null
     *
     * @param id the id of the lyDoCongTac to save.
     * @param lyDoCongTac the lyDoCongTac to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lyDoCongTac,
     * or with status {@code 400 (Bad Request)} if the lyDoCongTac is not valid,
     * or with status {@code 404 (Not Found)} if the lyDoCongTac is not found,
     * or with status {@code 500 (Internal Server Error)} if the lyDoCongTac couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ly-do-cong-tacs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LyDoCongTac> partialUpdateLyDoCongTac(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LyDoCongTac lyDoCongTac
    ) throws URISyntaxException {
        log.debug("REST request to partial update LyDoCongTac partially : {}, {}", id, lyDoCongTac);
        if (lyDoCongTac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lyDoCongTac.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lyDoCongTacRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LyDoCongTac> result = lyDoCongTacService.partialUpdate(lyDoCongTac);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lyDoCongTac.getId().toString())
        );
    }

    /**
     * {@code GET  /ly-do-cong-tacs} : get all the lyDoCongTacs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lyDoCongTacs in body.
     */
    @GetMapping("/ly-do-cong-tacs")
    public ResponseEntity<List<LyDoCongTac>> getAllLyDoCongTacs(
        LyDoCongTacCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get LyDoCongTacs by criteria: {}", criteria);
        Page<LyDoCongTac> page = lyDoCongTacQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ly-do-cong-tacs/count} : count all the lyDoCongTacs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ly-do-cong-tacs/count")
    public ResponseEntity<Long> countLyDoCongTacs(LyDoCongTacCriteria criteria) {
        log.debug("REST request to count LyDoCongTacs by criteria: {}", criteria);
        return ResponseEntity.ok().body(lyDoCongTacQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ly-do-cong-tacs/:id} : get the "id" lyDoCongTac.
     *
     * @param id the id of the lyDoCongTac to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lyDoCongTac, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ly-do-cong-tacs/{id}")
    public ResponseEntity<LyDoCongTac> getLyDoCongTac(@PathVariable Long id) {
        log.debug("REST request to get LyDoCongTac : {}", id);
        Optional<LyDoCongTac> lyDoCongTac = lyDoCongTacService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lyDoCongTac);
    }

    /**
     * {@code DELETE  /ly-do-cong-tacs/:id} : delete the "id" lyDoCongTac.
     *
     * @param id the id of the lyDoCongTac to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ly-do-cong-tacs/{id}")
    public ResponseEntity<Void> deleteLyDoCongTac(@PathVariable Long id) {
        log.debug("REST request to delete LyDoCongTac : {}", id);
        lyDoCongTacService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
