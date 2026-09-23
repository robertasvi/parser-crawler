# parser-crawler

`parser-crawler` exposes a small REST API for inspecting configured crawl inputs and triggering article categorization.

## Exposed APIs

### `GET /version`
- **Use case:** confirm the deployed service version.
- **Response:** plain text version string.

### `GET /status`
- **Use case:** quick liveness check for dashboards, smoke tests, or manual verification.
- **Response:** plain text status message.

### `GET /stop`
- **Use case:** stop or simulate stopping an active crawl from an external caller.
- **Response:** plain text confirmation message.

### `GET /start`
- **Use case:** run the crawler and return discovered article URLs grouped by category.
- **Response:** JSON object where each key is a category tag and each value is a list of article URIs.
- **Typical workflow:** call this endpoint after verifying available sources and categories with the `/source/*` endpoints.

### `GET /source/verify`
- **Use case:** confirm that source-related endpoints are reachable before integrating with them.
- **Response:** plain text verification message.

### `GET /source/list`
- **Use case:** retrieve the configured source websites that the crawler reads from.
- **Response:** JSON array of source objects with fields such as `name`, `raw`, `schema`, `www`, `subDomain`, `domain`, `path`, and `attributes`.
- **Typical workflow:** use this endpoint to show operators which news sources are currently enabled.

### `GET /source/category/list`
- **Use case:** retrieve the supported output categories used by the crawler.
- **Response:** JSON array of category objects with `name`, `description`, and `tag`.
- **Typical workflow:** use these tags to interpret the grouped result returned by `GET /start`.

## Example usage

1. Call `GET /source/list` to see which sites are configured.
2. Call `GET /source/category/list` to learn which categories the crawler can return.
3. Call `GET /start` to crawl configured sources and receive categorized article links.
4. Call `GET /status` to verify the service is still responsive.

## Notes

- All exposed endpoints currently use `GET`.
- Swagger/Springfox is enabled in the application, so API documentation can also be explored through the generated Swagger UI when the service is running.
