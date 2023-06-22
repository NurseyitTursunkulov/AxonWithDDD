package com.example.auth.domain.aggregate.article

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

class OffsetBasedPageRequest
/** Creates OffsetBasedPageRequest instance.  */(
    private val offset: Int,
    private val limit: Int,
    private val sort: Sort
) : Pageable {
    override fun getPageNumber(): Int {
        return offset / limit
    }

    override fun getPageSize(): Int {
        return limit
    }

    override fun getOffset(): Long {
        return offset.toLong()
    }

    override fun getSort(): Sort {
        return sort
    }

    override fun next(): Pageable {
        return OffsetBasedPageRequest(getOffset().toInt() + pageSize, pageSize, getSort())
    }

    private fun previous(): Pageable {
        // The integers are positive. Subtracting does not let them become bigger than integer.
        return if (hasPrevious()) OffsetBasedPageRequest(getOffset().toInt() - pageSize, pageSize, getSort()) else this
    }

    override fun previousOrFirst(): Pageable {
        return if (hasPrevious()) previous() else first()
    }

    override fun first(): Pageable {
        return OffsetBasedPageRequest(0, pageSize, getSort())
    }

    override fun withPage(pageNumber: Int): Pageable {
        return OffsetBasedPageRequest((pageNumber - 1) * limit, limit, sort)
    }


    override fun hasPrevious(): Boolean {
        return offset > limit
    }

    companion object {
        fun of(offset: Int, limit: Int, sort: Sort): OffsetBasedPageRequest {
            return OffsetBasedPageRequest(offset, limit, sort)
        }
    }
}