# Application Description

## Overview
`parser-crawler` is a Spring Boot REST application that crawls configured news source pages, extracts article links, and groups those links into predefined content categories.

## What the application does
- Loads source website definitions from the `SourceService`.
- Fetches source pages with Jsoup.
- Extracts anchor (`<a>`) links and normalizes them to absolute URIs for the same domain.
- Filters likely article links and removes duplicates and comment links.
- Categorizes each article URI using keyword-based matching.
- Returns categorized results through a REST endpoint.

## API surface
- `GET /start` — runs crawl and returns categorized article URI lists.
- `GET /status` — returns crawler status text.
- `GET /stop` — returns stop confirmation text.
- `GET /version` — returns API version text.
- `GET /source/verify` — verifies source endpoint availability.
- `GET /source/list` — returns configured source definitions.
- `GET /source/category/list` — returns available output categories.

## Core components
- `CrawlerApi` — exposes crawl lifecycle endpoints.
- `SourceApi` — exposes source and category metadata endpoints.
- `CrawlerService` — crawling, URI normalization, filtering, and categorization logic.
- `SourceService` — source configuration and category keyword dictionaries.

## Output format
The crawl result is a JSON object where each key is a category tag and each value is a list of article URIs that matched that category.

## Technology stack
- Java 8
- Spring Boot (Web)
- Jsoup for HTML parsing
- Springfox (Swagger) for API documentation
